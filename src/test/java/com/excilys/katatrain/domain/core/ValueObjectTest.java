package com.excilys.katatrain.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ValueObjectTest<T> {
    @Test
    public void should_be_a_value_object() {
        T actual = get();
        T expected = get();
        assertThat(actual)
                .isNotSameAs(expected)
                .isEqualTo(expected);
    }

    protected abstract T get();
}