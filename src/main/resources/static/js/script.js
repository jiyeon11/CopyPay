function removeNonNumbers(input) {  //숫자만 남기고 제거하는 함수
    return input.replace(/[^0-9]/g, '');
}

function removeDateSymbols(input) {  //날짜 형식에서 기호 제거하는 함수
    return input.replace(/[-/]/g, '');
}

function isEightDigitNumber(input) {  //8자리 숫자 체크 함수
    return /^\d{8}$/.test(input);
}

function formatBusinessRegNumber(regNumber) {  //사업자번호 xxx-xx-xxxxx 형식으로 바꾸기
    let formatRegNumber = typeof regNumber === 'string' ? regNumber : $(regNumber).val();
    if (formatRegNumber.length === 10) {
        formatRegNumber = formatRegNumber.substring(0, 3) + '-' + formatRegNumber.substring(3, 5) + '-' + formatRegNumber.substring(5);
    }
    $(regNumber).val(formatRegNumber);
    return formatRegNumber;
}

function formatDate(date) {  // 출력, 입력 날짜 YYYY/MM/DD 형식으로 바꾸기
    let dateString = '';
    if(typeof date !== 'string'){  // 날짜를 입력받는 경우
        for (let i = 0; i < date.value.length; i++) {
            if (!isNaN(date.value[i]) && date.value[i] !== ' ') {  // 숫자만 입력받음
                dateString += date.value[i];
            }
        }
    }else{  // 조회에서 날짜를 출력하는 경우
        dateString = date;
    }
    if (dateString.length > 8)  // 8자리까지만 입력 가능
        dateString = dateString.substring(0, 8);
    if (dateString.length >= 4)
        dateString = dateString.substring(0, 4) + '/' + dateString.substring(4);
    if (dateString.length >= 7)
        dateString = dateString.substring(0, 7) + '/' + dateString.substring(7);
    $(date).val(dateString);
    return dateString;
}

function formatPhone(phone) {  // 전화번호 형식으로 바꾸기
    let phoneNumber = typeof phone === 'string' ? phone : $(phone).val();
    if (phoneNumber.length === 11) {
        let first = phoneNumber.substring(0, 3);
        let second = phoneNumber.substring(3, 7);
        let third = phoneNumber.substring(7);
        phoneNumber = first + '-' + second + '-' + third;
    }
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

function extractFieldName(field) {  //필드명 추출 및 변환 (첫 글자를 대문자로 변경)
    var parts = field.split('.');
    parts = parts.length > 1 ? parts[1] : field; //두 번째 부분 반환
    return parts.charAt(0).toUpperCase() + parts.slice(1);
}

function validateInputDate() {  //기간 입력값 검증
    const startDate = $('#startDate').val();
    const endDate = $('#endDate').val();
    
    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
        alert('시작일이 종료일보다 늦을 수 없습니다.');
        return false;
    }
    return true;
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
            $('.error-message').text('');//모든 필드의 오류 메시지 초기화
            successCallback(responseData);
        },
        error: function (error) {
            if (errorCallback) {
                errorCallback();
            }
            if (error.responseJSON) {
                const errors = error.responseJSON;
                if (errors.status) {  //상태코드가 있으면 alert 띄움
                    alert(errors.message);
                } else {
                    $("#memoModal").addClass("hidden");  //메모 모달 창 숨김
                    //모든 필드의 오류 메시지 초기화
                    $('.error-message').text('');
                    //오류 메시지를 각 필드에 맞게 표시
                    for (const field in errors) {
                        $("p[name=error" + extractFieldName(field) + "]").text(errors[field]);
                    }
                }
            }
            else if (!error.responseJSON || !error.responseJSON.message) {
                alert("다시 시도해 주세요.");
            }
        }
    });
}

function showEmptyDataRow(cols) {  //조회 테이블의 조회 결과가 없을 때
    const tbody = $('#list');
    $('#pagination').empty();
    tbody.empty();
    const noDataRow = `
                    <tr class="table-row">
                        <td class="text-center pt-36" colspan="${cols}">
                            <p class="text-lg">조회 결과가 없습니다.</p>
                        </td>
                    </tr>
                `;
    tbody.append(noDataRow);
}

function renderPagination(data) {  //페이징
    pageIndex = data.currentPageNo;  //현재 페이지로 변경
    const page = $('#pagination');
    page.empty();
    var pagination = `<ol class="absolute left-1/2 transform -translate-x-1/2 inline-flex space-x-2 whitespace-nowrap center" id="pagination">`;
    var isPrev = pageIndex > 1? pageIndex-1 : pageIndex;  //이전 버튼
    pagination += `<li><a href="javascript:void(0);" onclick="fetchAndDisplayPage(1); return false;" class="px-1 -mx-1 border rounded-lg hover:bg-blue-500 hover:text-white transition duration-200">&lt;&lt;</a></li>`;
    pagination += `<li><a href="javascript:void(0);" onclick="fetchAndDisplayPage(${isPrev}); return false;" class="px-1 -mx-1 border rounded-lg hover:bg-blue-500 hover:text-white transition duration-200">&lt;</a></li>`;

    for (var i = data.firstPageNoOnPageList; i <= data.lastPageNoOnPageList; i++) {  // 숫자들
        pagination += `<li><a href="javascript:void(0);" onclick="fetchAndDisplayPage(${i}); return false;" class="px-2 border rounded-lg hover:bg-blue-500 hover:text-white transition duration-200" id="page-${i}">${i}</a></li>`;
    }

    var isNext = pageIndex < data.realEnd? pageIndex+1 : pageIndex;  //다음 버튼
    pagination += `<li class="next"><a href="javascript:void(0);" onclick="fetchAndDisplayPage(${isNext}); return false;" class="px-1 -mx-1 border rounded-lg hover:bg-blue-500 hover:text-white transition duration-200">&gt;</a></li>`;
    pagination += `<li><a href="javascript:void(0);" onclick="fetchAndDisplayPage(${data.realEnd}); return false;" class="px-1 -mx-1 border rounded-lg hover:bg-blue-500 hover:text-white transition duration-200">&gt;&gt;</a></li>`;
    pagination += `</ol>`;
    pagination += `<p class="text-sm ml-1 float-left inline-flex text-slate-600">${data.firstRecordIndex+1}-${data.firstRecordIndex+data.length}/${data.totalCount}건 [${pageIndex}/${data.realEnd} 페이지] 조회되었습니다.</P>`
    page.append(pagination);
    $(`#page-${data.currentPageNo}`).addClass("text-white bg-blue-500");  //현재 페이지 표시
}