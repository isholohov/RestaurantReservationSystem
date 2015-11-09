<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
</head>
<body>
<script language="JavaScript">
    function setAllCheckOff(obj) {
        var items = obj.form.getElementsByTagName("input"),
                len, i;
        for (i = 0, len = items.length; i < len; i += 1) {
            if (items.item(i).type && items.item(i).type === "checkbox") {
                if (obj.checked) {
                    items.item(i).checked = false;
                }
            }
        }
    }
</script>
<span class="content-wrapper">
    <form name="filter" action="/index" method="GET">

        <div id="dish">Dish</div>

        <div id="dish-list">
            <% if (request.getAttribute("setDishTags") != null) {
                Set setDishTags = (Set) request.getAttribute("setDishTags");
                for (Object objDishTag : setDishTags) {
                    if (objDishTag != null) {
                        String dishTag = objDishTag.toString();
            %>
            <input type="checkbox" name="dishTag" value="<%=dishTag%>" id="<%=dishTag%>"><%=dishTag%><Br>
            <%
                        }
                    }
                }
            %>
        </div>

        <div id="dish-filter-buttons">
            <input type="submit" value="Apply">
            <input type="reset" value="Reset" onclick="setAllCheckOff(this)">
        </div>
    </form>

    <%
        String[] valuesForFilter = request.getParameterValues("dishTag");
        if (valuesForFilter != null) {
            for (int i = 0; i < valuesForFilter.length; i++) {
    %>
    <script language="JavaScript">
        document.getElementById("<%=valuesForFilter[i]%>").checked = true;
    </script>
    <%
            }
        }
    %>

</span>


</body>
</html>
