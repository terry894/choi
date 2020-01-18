package com.newlecture.web.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class NewDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   protected Class<?>[] getRootConfigClasses() {
      
      return new Class[] {
            ServiceContextConfig.class,
            SMTPConfig.class,
            SecurityContextConfig.class
      };
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      
      return new Class[] {
            ServletContextConfig.class,
            NewMvcConfigurer.class,
            TilesConfig.class,
            NewWebSocketConfig.class
      };
   }

   @Override
   protected String[] getServletMappings() {
      
      return new String[] {"/"};
   }
   
   @Override
   protected Filter[] getServletFilters() {
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
      filter.setEncoding("UTF-8");
      filter.setForceEncoding(true);
      return new Filter[] {
            filter
      };
   }

}