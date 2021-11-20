<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper name="${pageName}">
    <div class="container add-view">
        <div class="row">
            <c:if test="${status == 'success'}">
                <div class="alert alert-success" role="alert">
                    Успешно добавлено!
                </div>
            </c:if>
            <c:if test="${status == 'error'}">
                <div class="alert alert-danger" role="alert">
                    При добавлении возникла ошибка!
                </div>
            </c:if>
        </div>
        <div class="row">
            <form method="post" action="add" autocomplete="off">
                <div class="form-group">
                    <label for="transportType">Тип транспорта</label>
                    <select class="form-select" aria-label="Default select example" id="transportType" name="transportType">
                        <option value="train">Поезд</option>
                        <option value="airplane">Самолет</option>
                        <option value="ship">Корабль</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="raceName">Название рейса</label>
                    <input type="text" class="form-control" id="raceName" name="raceName" required>
                </div>
                <div class="form-group">
                    <label for="datetimepicker">Дата</label>
                    <input type="text" class="form-control" id="datetimepicker" name="date" required>
                </div>
                <button type="submit" class="btn btn-dark form-control">Добавить</button>
            </form>
        </div>
    </div>
</t:wrapper>