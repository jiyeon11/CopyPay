const onlyNumbersRegex = /[^0-9]/g;  //숫자만 남기고 제거하는 정규식
const removeDateSymbolsRegex = /[-/]/g;  //날짜 형식에서 기호 제거하는 정규식
const businessRegNumberRegex = /(\d{3})(\d{2})(\d{5})/;  //사업자번호 형식으로 변환하는 정규식
const phoneNumberFormatRegex = /(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/;  //전화번호 형식으로 변환하는 정규식
const eightDigitCheckRegex = /^\d{8}$/;  //8자리 숫자 체크하는 정규식
const numberFormatReplacement = "$1-$2-$3";  //자릿수에 맞춰 '-' 추가하는 정규식

function formatBusinessRegNumber(regNumber) {  //사업자번호 xxx-xx-xxxxx 형식으로 바꾸기
    let formatRegNumber = typeof regNumber === 'string' ? regNumber : $(regNumber).val();
    formatRegNumber = formatRegNumber
        .replace(onlyNumbersRegex, '')
        .replace(businessRegNumberRegex, numberFormatReplacement);
    $(regNumber).val(formatRegNumber);
    return formatRegNumber;
}

function formatDate(dateString) {  //날짜 YYYY/MM/DD 형식으로 바꾸기
    if (!eightDigitCheckRegex.test(dateString)) {
        console.error('잘못된 날짜 형식입니다:', dateString);
        return '';
    }
    const year = dateString.substring(0, 4);
    const month = dateString.substring(4, 6);
    const day = dateString.substring(6, 8);
    return year + '/' + month + '/' + day;
}

function formatPhone(phone) {  //전화번호 형식으로 바꾸기
    let phoneNumber = typeof phone === 'string' ? phone : $(phone).val();
    phoneNumber = phoneNumber
        .replace(onlyNumbersRegex, '')
        .replace(phoneNumberFormatRegex, numberFormatReplacement);
    $(phone).val(phoneNumber);
    return phoneNumber;
}

function setTodayDate() {  //오늘 날짜로 설정
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    const formattedDate = year + '/' + month + '/' + day;
    return formattedDate;
}

function extractFieldName(field) {  //검증 실패한 필드 이름 추출
    var parts = field.split('.');
    parts = parts.length > 1 ? parts[1] : field; //두 번째 부분 반환
    return parts.charAt(0).toUpperCase() + parts.slice(1);
}

/**
 * AJAX 요청을 처리하는 공통 함수
 * @param {string} url - 요청 URL
 * @param {string} method - HTTP 메서드
 * @param {object} data - 전송할 데이터 (JSON 형식)
 * @param {function} successCallback - 성공 시 콜백 함수
 * @param {function} errorCallback - 에러 시 콜백 함수
 */
function makeAjaxCall(url, method, data, successCallback, errorCallback) {
    const token = $("meta[name='_csrf']").attr("content")
    const header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        url: url,
        method: method,
        data: method !== 'GET' ? JSON.stringify(data) : $.param(data),
        contentType: method !== 'GET' ? 'application/json; charset=utf-8' : undefined,
        beforeSend: function (xhr) {  //CSRF 토큰 설정
            xhr.setRequestHeader(header, token);
        },
        success: function (responseData) {
            successCallback(responseData);
        },
        error: function (error) {
            if (errorCallback) {
                errorCallback();
            }
            else if (error.responseJSON) {
                const errors = error.responseJSON;
                //모든 필드의 오류 메시지 초기화
                $('.error-message').text('');

                //오류 메시지를 각 필드에 맞게 표시
                for (const field in errors) {
                    $("p[name=error" + extractFieldName(field) + "]").text(errors[field]);
                }
            }
            else if (!error.responseJSON.message) {
                alert("다시 시도해 주세요.");
            }
        }
    });
}