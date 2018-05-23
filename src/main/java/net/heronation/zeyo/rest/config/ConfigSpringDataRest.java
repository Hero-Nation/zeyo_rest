package net.heronation.zeyo.rest.config;

import java.util.List;

import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.controller.company_no_history.CompanyNoHistoryDistinctNameConverter;
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.material.Material;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.warranty.Warranty;

@Slf4j
@Component 
public class ConfigSpringDataRest extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureConversionService(ConfigurableConversionService conversionService) {
		log.debug("configureConversionService");
		conversionService.addConverter(new CompanyNoHistoryDistinctNameConverter());
		super.configureConversionService(conversionService);
	}

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		log.debug("configureValidatingRepositoryEventListener");
		// TODO Auto-generated method stub
		super.configureValidatingRepositoryEventListener(validatingListener);
	}

	@Override
	public void configureExceptionHandlerExceptionResolver(ExceptionHandlerExceptionResolver exceptionResolver) {
		log.debug("configureExceptionHandlerExceptionResolver");
		// TODO Auto-generated method stub
		//super.configureExceptionHandlerExceptionResolver(exceptionResolver);
		
		
	}

	@Override
	public void configureHttpMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		log.debug("configureHttpMessageConverters");
		// TODO Auto-generated method stub
		super.configureHttpMessageConverters(messageConverters);
	}

	@Override
	public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
		log.debug("configureJacksonObjectMapper");
		// TODO Auto-generated method stub
		super.configureJacksonObjectMapper(objectMapper);
		objectMapper.registerModule((new Hibernate5Module()));
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		log.debug("configureRepositoryRestConfiguration");
		config.setBasePath("/api");

		// config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.VISIBILITY);

		config.exposeIdsFor(Bbs.class);
		config.exposeIdsFor(Brand.class);
		config.exposeIdsFor(Category.class);
		config.exposeIdsFor(ClothColor.class);
		config.exposeIdsFor(CompanyNoHistory.class);
		config.exposeIdsFor(FitInfo.class);
		config.exposeIdsFor(FitInfoOption.class);
		config.exposeIdsFor(Item.class);
		config.exposeIdsFor(ItemBleachMap.class);
		config.exposeIdsFor(ItemClothColorMap.class);
		config.exposeIdsFor(ItemDrycleaningMap.class);
		config.exposeIdsFor(ItemDrymethodMap.class);
		config.exposeIdsFor(ItemIroningMap.class);
		config.exposeIdsFor(ItemLaundryMap.class);
		config.exposeIdsFor(ItemMaterialMap.class);
		config.exposeIdsFor(ItemShopmallMap.class);
		config.exposeIdsFor(ItemSizeOptionMap.class);
		config.exposeIdsFor(Kindof.class);
		config.exposeIdsFor(Madein.class);
		config.exposeIdsFor(Material.class);
		config.exposeIdsFor(MeasureItem.class);
		config.exposeIdsFor(Member.class);
		config.exposeIdsFor(Shopmall.class);
		config.exposeIdsFor(SizeOption.class);
		config.exposeIdsFor(SubCategory.class);
		config.exposeIdsFor(SubCategoryFitInfoMap.class);
		config.exposeIdsFor(SubCategoryMeasureMap.class);
		config.exposeIdsFor(Warranty.class);

	}
}