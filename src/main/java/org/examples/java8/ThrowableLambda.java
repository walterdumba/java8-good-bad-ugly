package org.examples.java8;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ThrowableLambda<T, E extends SQLException> {
    T execute(PreparedStatement statement) throws E;
}
