package at.irian.gui.util;

import org.apache.myfaces.extensions.cdi.core.api.provider.BeanManagerProvider;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.util.Set;

/**
 *
 */
public class CdiUtil {

    public static <T> T resolveBean(Class<T> clazz) {
        BeanManager beanManager = BeanManagerProvider.getInstance().getBeanManager();
        Set<Bean<?>> beans = beanManager.getBeans(clazz);
        Bean<?> bean = beanManager.resolve(beans);
        return (T)beanManager.getReference(bean, clazz, beanManager.createCreationalContext(bean));
    }

}
