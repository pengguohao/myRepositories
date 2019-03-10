<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul style="padding-left:0px;" class="list-group">
	<c:forEach items="${userMenu.children}" var="menuInfo">
		<c:if test="${empty menuInfo.children}">
			<li class="list-group-item tree-closed" >
				<a href="${APP_PATH}${menuInfo.url}"><i class="${menuInfo.icon}">&nbsp;&nbsp;</i>${menuInfo.name}</a> 
			</li>
		</c:if>	
		<c:if test="${not empty menuInfo.children }">
			<li class="list-group-item">
			<span><i class="${menuInfo.icon}">&nbsp;&nbsp;</i>${menuInfo.name} <span class="badge" style="float:right">${menuInfo.children.size()}</span></span> 
			<ul style="margin-top:10px;">
			<c:forEach items="${menuInfo.children}" var="childMenu">
				<li style="height:30px;">
					<c:if test="${currentMenuUrl==childMenu.url}">
						<a href="${APP_PATH}${childMenu.url}" style="color:red;" id="${childMenu.id}">
					</c:if>
					<c:if test="${currentMenuUrl!=childMenu.url}">
						<a href="${APP_PATH}${childMenu.url}" id="${childMenu.id}">
					</c:if>
					<i class="${childMenu.icon}">&nbsp;</i>${childMenu.name}</a> 
				</li>
			</c:forEach>
			</ul>
			</li>
		</c:if>
	</c:forEach>
	
</ul>
<script type="text/javascript">
<!--

//-->
</script>