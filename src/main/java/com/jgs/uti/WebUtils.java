package com.jgs.uti;

import cn.hutool.core.util.ArrayUtil;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {
	private  final String BASIC_ = "Basic ";
	private  final String UNKNOWN = "unknown";
	private static final String IP_LOCAL = "127.0.0.1";
	private static final int IP_LEN = 15;

	/**
	 * 获取ip
	 *
	 * @return {String}
	 */
	public String getIP() {
		String[] ips = getAccessIP(getRequest());
		String ip = ips[ips.length - 1].trim();//获取真实ip
		return ip;
	}

	/**
	 * 获取ip
	 *
	 * @param request HttpServletRequest
	 * @return {String}
	 */
	public String[] getAccessIP(HttpServletRequest request) {
		Assert.notNull(request, "HttpServletRequest is null");
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(StringUtils.isEmpty(ip)){
			return null;
		}
		String[] ips = ip.split(",");
		String[] newIps = null;
		if(ips.length > 4){
			newIps = ArrayUtil.sub(ips,0,ips.length-3);
		}else{
			newIps = ArrayUtil.sub(ips,0,1);
		}
		return newIps;
	}

	/**
	 * 获取 HttpServletRequest
	 *
	 * @return {HttpServletRequest}
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
