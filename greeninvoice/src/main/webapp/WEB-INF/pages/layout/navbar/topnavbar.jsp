<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- top navigation -->
        <div class="top_nav footer_fixed">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                  <c:choose>
                            <c:when test="${user.profileImage!=null}">
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/${user.profileImage}' class="img-circle" alt="User Image">
                            </c:when>
                            <c:otherwise>
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/img_avatar1.png' class="img-circle" alt="User Image">
                            </c:otherwise>
                  </c:choose>${user.firstName}
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:showProfile()"> Profile</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>Settings</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">Help</a></li>
                    <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>

                <li role="presentation" class="dropdown">
                  <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-envelope-o"></i>
                    <span class="badge bg-green">2</span>
                  </a>
                  <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                    <li>
                      <a>
                      <span class="image">
                      <c:choose>
                            <c:when test="${user.profileImage!=null}">
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/${user.profileImage}' class="img-circle" alt="User Image">
                            </c:when>
                            <c:otherwise>
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/img_avatar1.png' class="img-circle" alt="User Image">
                            </c:otherwise>
                        </c:choose>
<!--                         <img src="images/img_avatar1.png" alt="Profile Image" /> -->
                        </span>
                        <span>
                          <span>${user.firstName}</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          first message here...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                      <span class="image">
                      <c:choose>
                            <c:when test="${user.profileImage!=null}">
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/${user.profileImage}' class="img-circle" alt="User Image">
                            </c:when>
                            <c:otherwise>
                                <img src='${pageContext.request.contextPath}/resources/images/profile-pictures/img_avatar1.png' class="img-circle" alt="User Image">
                            </c:otherwise>
                        </c:choose>
<!--                         <img src="images/img_avatar1.png" alt="Profile Image -->
                        </span>
                        <span>
                          <span>${user.firstName}</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          second message here...
                        </span>
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
<!-- /top navigation -->
