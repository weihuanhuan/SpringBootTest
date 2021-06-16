/*
 *    Copyright 2015-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package sample.mybatis.xml;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sample.mybatis.xml.dao.CityDao;
import sample.mybatis.xml.mapper.HotelMapper;

import javax.annotation.Resource;

/**
 * 在非 web 环境中使用 executable jar 来运行 spring boot application。
 */
@SpringBootApplication
public class SampleXmlApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SampleXmlApplication.class, args);
    }

    /**
     * 无注入标记的域
     *
     * 这里没有显式的任何注解来标记该域的可注入信息，但是运行时其域依旧有值，这是为什么呢？
     * 对 spring debug 发现其注入的时机是发生在实例化 SampleXmlApplication 对象的时候，spring 会根据对象的构造器来进行依赖注入。
     *
     * 注意这里的构造是对构造参数进行注入，所以该对象不能有默认的 无参 构造器，否则 spring 会默认使用无参构造器，进而会导致这种没有注入标记的域不执行注入逻辑，产生 NPE 问题。
     */
    private CityDao cityDao;

    /**
     * 第二步：构造对象之后 spring 依据注解信息进行额外的域值注入。
     *
     * 显式注入标记的域
     *
     * 这里的域显示使用了 java annotation @Resource 来指示 spring 对该域进行处理。
     * 这会导致 spring 在构建完 SampleXmlApplication 对象后执行额外的属性注入行为。
     *
     * 注意通过这种显式的标记方法，我们将非构造参数的域进行注入，从而决解那些不方便构造传参的域的赋值问题。
     *
     * "main@1" prio=5 tid=0x1 nid=NA runnable
     *   java.lang.Thread.State: RUNNABLE
     * 	  at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.autowireResource(CommonAnnotationBeanPostProcessor.java:515)
     * 	  at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.getResource(CommonAnnotationBeanPostProcessor.java:496)
     * 	  at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor$ResourceElement.getResourceToInject(CommonAnnotationBeanPostProcessor.java:627)
     * 	  at org.springframework.beans.factory.annotation.InjectionMetadata$InjectedElement.inject(InjectionMetadata.java:171)
     * 	  at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:87)
     * 	  at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.postProcessPropertyValues(CommonAnnotationBeanPostProcessor.java:318)
     * 	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1269)
     * 	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:551)
     * 	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:481)
     */
    @Resource
    private HotelMapper hotelMapper;

    /**
     * 第一步：构造对象时 spring 依据构造参数进行初步的域值注入。
     *
     * "main@1" prio=5 tid=0x1 nid=NA runnable
     *   java.lang.Thread.State: RUNNABLE
     * 	  at sample.mybatis.xml.SampleXmlApplication.<init>(SampleXmlApplication.java:70)
     * 	  at sample.mybatis.xml.SampleXmlApplication$$EnhancerBySpringCGLIB$$a5a07284.<init>(<generated>:-1)
     * 	  at sun.reflect.NativeConstructorAccessorImpl.newInstance0(NativeConstructorAccessorImpl.java:-1)
     * 	  at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
     * 	  at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
     * 	  at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
     * 	  at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:142)
     * 	  at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:122)
     * 	  at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:271)
     * 	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1198)
     * 	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1100)
     * 	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:511)
     * 	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:481)
     */
    public SampleXmlApplication(CityDao cityDao, HotelMapper hotelMapper) {
        this.cityDao = cityDao;
        this.hotelMapper = hotelMapper;
    }

    @Override
    @SuppressWarnings("squid:S106")
    public void run(String... args) {
        System.out.println(this.cityDao.selectCityById(1));
        System.out.println(this.hotelMapper.selectByCityId(1));
    }

}
