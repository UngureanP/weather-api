package com.example.weather;



import static org.junit.Assert.*;

import com.example.weather.service.WeatherApiService;
import com.example.weather.util.DataCatcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class WeatherApplicationTests {

	private LocalDate localDate = LocalDate.now();
	private List<String> cityList = Arrays.asList("London", "Moscow", "Tokyo");

	@Mock
	WeatherApiService weatherApiService;

	@Test
	public void validateCitiesInCache() {
		cityList.forEach(p -> weatherApiService.getWeatherByCityAndDate(p, localDate));
		assertTrue(DataCatcher.cache.size() == 3);
	}

}
