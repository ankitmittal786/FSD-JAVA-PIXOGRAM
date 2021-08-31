package com.pixogram.common;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonUtils {

	private CommonUtils() {
		// private constructor to prevent instantiation
	}

	/**
	 * @description ignore null properties
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames(final Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		final java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		final Set<String> emptyNames = new HashSet<>();
		for (final java.beans.PropertyDescriptor pd : pds) {
			try {
				final Object srcValue = src.getPropertyValue(pd.getName());
				if (srcValue == null) {
					emptyNames.add(pd.getName());
				}
			} catch (final RuntimeException e) {
				if (log.isInfoEnabled()) {
					log.info(e.getMessage());
				}
			} catch (final Exception e) {
				if (log.isInfoEnabled()) {
					log.info(e.getMessage());
				}
			}

		}
		final String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * @description this method is used to get logged user name
	 * @return
	 */
//	public static String getPrincipal() {
//		return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//	}

	/**
	 *
	 * @param amount
	 * @return
	 */
	public static double roundOf(final double amount) {
		return Math.round(amount * 100.0) / 100.0;
	}

//	/**
//	 *
//	 * @return
//	 */
//	public static String getPrincipal() {
//		// return
//		// SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//		if ((SecurityContextHolder.getContext() != null)
//				&& (SecurityContextHolder.getContext().getAuthentication() != null)
//				&& (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)) {
//			final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			if (principal instanceof UserDetails) {
//				return ((UserDetails) principal).getUsername();
//			}
//			return principal.toString();
//		}
//		return null;
//	}

	
}
