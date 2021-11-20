<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper name="${pageName}">
    <div class="container">
        <c:if test="${pageName == 'search'}">
        <div class="row">
            <h3 style="margin-bottom: 22px;">Поиск: ${searchValue}</h3>
        </div>
        </c:if>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Тип</th>
                    <th scope="col">Название рейса</th>
                    <th scope="col">Дата</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tableElement" items="${tableData}" varStatus="i">
                <tr class="tr1">
                    <td>${tableElement.getTypeName()}</td>
                    <td>${tableElement.getRaceName()}</td>
                    <td>${tableElement.getFormatDate()}</td>
                </tr>
                </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</t:wrapper>