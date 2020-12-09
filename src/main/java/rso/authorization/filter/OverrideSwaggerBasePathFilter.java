package rso.authorization.filter;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;
import static springfox.documentation.swagger.common.SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.swagger.models.Swagger;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.web.SwaggerTransformationContext;
import springfox.documentation.swagger2.web.WebMvcSwaggerTransformationFilter;

@Component
@Order(SWAGGER_PLUGIN_ORDER)
public class OverrideSwaggerBasePathFilter implements WebMvcSwaggerTransformationFilter
{

    @Value("${my.swagger.prefix}")
    private String contextPath;

    @Override
    public Swagger transform(final SwaggerTransformationContext<HttpServletRequest> context) {
        Swagger swagger = context.getSpecification();
        return swagger.basePath(contextPath + swagger.getBasePath());
    }

    @Override
    public boolean supports(final DocumentationType documentationType) {
        return SWAGGER_2.equals(documentationType);
    }
}