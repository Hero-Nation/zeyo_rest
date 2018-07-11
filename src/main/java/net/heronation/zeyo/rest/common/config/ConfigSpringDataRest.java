package net.heronation.zeyo.rest.common.config;

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
import net.heronation.zeyo.rest.bbs.repository.Bbs;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColor;
import net.heronation.zeyo.rest.company_no_history.controller.CompanyNoHistoryDistinctNameConverter;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.fit_info.repository.FitInfo;
import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOption;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMap;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMap;
import net.heronation.zeyo.rest.item_drycleaning_map.repository.ItemDrycleaningMap;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMap;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMap;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.kindof.repository.Kindof;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.material.repository.Material;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItem;
import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.warranty.repository.Warranty;

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