<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
              <c:choose>
                            <c:when test="${user.profileImage!=null}">
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/${user.profileImage}' class="img-circle profile_img" alt="...">
                            </c:when>
                            <c:otherwise>
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/img_avatar1.png' class="img-circle profile_img" alt="...">
               				</c:otherwise>
                </c:choose>
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>${user.firstName}</h2>
              </div>
            </div>
<!-- /menu profile quick info -->
