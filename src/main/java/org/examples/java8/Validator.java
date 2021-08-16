package org.examples.java8;

import org.examples.common.ValidatorException;

import java.util.Objects;

@FunctionalInterface
public interface Validator<T> {

    boolean validate(T t) throws ValidatorException;

    default Validator<T> and(Validator<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> validate(t) && other.validate(t);
    }

    default Validator<T> or(Validator<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> validate(t) || other.validate(t);
    }

    default Validator<T>and(Validator<?super T>other, String brokenField){
        return or(other).orElseThrow(new ValidatorException(brokenField));
    }

    default Validator<T>or(Validator<?super T>other, String brokenField){
        return or(other).orElseThrow(new ValidatorException(brokenField));
    }

    default <X extends ValidatorException> Validator<T> orElseThrow(X throwable){
        return t -> {
            if(!this.validate(t)){
                throw throwable;
            }
            return false;
        };
    }
}
