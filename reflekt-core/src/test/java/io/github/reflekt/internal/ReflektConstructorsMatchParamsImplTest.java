package io.github.reflekt.internal;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.reflekt.ReflektAllConstructors;
import io.github.reflekt.ReflektConstructorsMatchParams;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

public class ReflektConstructorsMatchParamsImplTest {

    private ReflektAllConstructors mocka = mock(ReflektAllConstructors.class);
    private final ReflektConstructorsMatchParams target = new ReflektConstructorsMatchParamsImpl(LazyBuilder.lazy(() -> mocka));

    @Test
    public void testGetConstructorsMatchParams() throws NoSuchMethodException {
        // given
        Set<Constructor> constructors = Arrays.stream(ATestClassForMe.class.getDeclaredConstructors()).collect(Collectors.toSet());
        when(mocka.getAllConstructors()).thenReturn(constructors);

        // when
        Set<Constructor> result = target.getConstructorsMatchParams(int.class, boolean.class);

        // then
        //noinspection JavaReflectionMemberAccess
        Constructor expected = ATestClassForMe.class.getDeclaredConstructor(this.getClass(), int.class, boolean.class);
        assertThat(result, hasSize(1));
        assertThat(result, contains(expected));
    }

    @SuppressWarnings("unused")
    private class ATestClassForMe {

        public ATestClassForMe(NullPointerException e) {
        }

        public ATestClassForMe(int i, boolean b) {
        }

        public ATestClassForMe() {
        }
    }
}

