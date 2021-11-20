<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper name="${pageName}">
    <div class="container remove-view">
        <div class="row">
            <c:if test="${status == 'success'}">
                <div class="alert alert-success" role="alert">
                    Успешно удалено!
                </div>
            </c:if>
            <c:if test="${status == 'error'}">
                <div class="alert alert-danger" role="alert">
                    При удалении произошла ошибка!
                </div>
            </c:if>
        </div>
        <div class="row">
            <form method="post" action="remove">
                <div class="form-group">
                    <label for="transportType">Тип транспорта</label>
                    <select class="form-select" aria-label="Default select example" id="transportType" name="transportType">
                        <option value="train">Поезд</option>
                        <option value="airplane">Самолет</option>
                        <option value="ship">Корабль</option>
                    </select>
                    <select class="form-select" aria-label="Default select example" id="transportElem" name="transportElem">
                    </select>
                </div>
                <button type="submit" class="btn btn-dark form-control">Удалить</button>
            </form>
        </div>
    </div>
    <script>
        let transports = [
        <c:forEach var="tableElement" items="${tableData}" varStatus="i">
            {"id": ${i.index},
             "type": "${tableElement.getTypeName()}",
             "name": "${tableElement.getRaceName()}",
             "date": "${tableElement.getFormatDate()}"},
        </c:forEach>
        ]

        function changeSelectorContent(transportType) {
            let transportSelector = $('#transportElem');
            transportSelector.text("");
            transports.filter(transport => transport["type"] === transportType).forEach(elem => function (elem) {
                let option = $("<option></option>")
                    .attr("value", elem["id"])
                    .text(elem["name"] + ", " + elem["date"]);
                transportSelector.append(option);
            }(elem));
        }

        $('#transportType').on('change', function() {
            switch (this.value) {
                case "train":
                    changeSelectorContent("Поезд");
                    break;
                case "airplane":
                    changeSelectorContent("Самолет");
                    break;
                case "ship":
                    changeSelectorContent("Корабль")
                    break;
            }
            if ($('#transportElem option').length === 0) {
                $('form button').prop('disabled', true);
            } else {
                $('form button').prop('disabled', false);
            }
        });

        $('#transportType').change();
    </script>
</t:wrapper>