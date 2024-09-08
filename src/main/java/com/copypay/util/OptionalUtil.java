package com.copypay.util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public final class OptionalUtil {

    private OptionalUtil() {
        // 유틸리티 클래스, 인스턴스화 방지
    }

    /**
     * 널 가능한 값을 Optional로 변환합니다.
     *
     * @param value 변환할 값
     * @param <T> 값의 타입
     * @return 값을 포함하는 Optional, 값이 null이면 빈 Optional 반환
     */
    public static <T> Optional<T> toOption(T value) {
        return Optional.ofNullable(value);
    }

    /**
     * Optional에 값이 있으면 그 값을 반환하고, 없으면 제공된 기본값을 반환합니다.
     *
     * @param optional 값을 가져올 Optional
     * @param defaultValue Optional이 비어있을 때 반환할 기본값
     * @param <T> 값의 타입
     * @return Optional에 값이 있으면 그 값, 없으면 기본값
     */
    public static <T> T getOrDefault(Optional<T> optional, T defaultValue) {
        return optional.orElse(defaultValue);
    }

    /**
     * 제공된 매핑 함수를 사용하여 Optional을 새로운 Optional로 변환합니다.
     * 원본 Optional이 비어있지 않고 매핑 결과가 null이 아닌 경우에만 수행됩니다.
     *
     * @param optional 매핑할 Optional
     * @param mapper 매핑 함수
     * @param <T> 원본 Optional의 타입
     * @param <U> 결과 Optional의 타입
     * @return 매핑된 값을 포함하는 새 Optional, 또는 빈 Optional
     */
    public static <T, U> Optional<U> flatMap(Optional<T> optional, Function<? super T, ? extends U> mapper) {
        return optional.map(mapper).flatMap(OptionalUtil::toOption);
    }

    /**
     * Optional이 비어있지 않고 주어진 조건을 만족하면 해당 Optional을 반환하고,
     * 그렇지 않으면 빈 Optional을 반환합니다.
     *
     * @param optional 필터링할 Optional
     * @param predicate 적용할 조건
     * @param <T> Optional의 타입
     * @return 조건을 만족하는 Optional, 또는 빈 Optional
     */
    public static <T> Optional<T> filter(Optional<T> optional, Predicate<? super T> predicate) {
        return optional.filter(predicate);
    }

    /**
     * 두 Optional을 결합하여 두 값의 쌍을 포함하는 단일 Optional을 만듭니다.
     * 입력 중 하나라도 비어있으면 빈 Optional을 반환합니다.
     *
     * @param opt1 첫 번째 Optional
     * @param opt2 두 번째 Optional
     * @param <T> 첫 번째 Optional의 타입
     * @param <U> 두 번째 Optional의 타입
     * @return 값 쌍을 포함하는 Optional, 또는 빈 Optional
     */
    public static <T, U> Optional<Pair<T, U>> zip(Optional<T> opt1, Optional<U> opt2) {
        return opt1.flatMap(t -> opt2.map(u -> new Pair<>(t, u)));
    }

    /**
     * 두 개의 값을 보관하는 간단한 쌍(Pair) 클래스입니다.
     *
     * @param <T> 첫 번째 값의 타입
     * @param <U> 두 번째 값의 타입
     */
    public static class Pair<T, U> {
        private final T first;
        private final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }
}