<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Animals farm</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital@0;1&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/index.js" defer></script>

</head>

<body>
<header>
    <div class="wrap">
        <div class="flex-a mb">
            <a href="${pageContext.request.contextPath}/">HOME</a>
            <a href="${pageContext.request.contextPath}/new-animal">NEW ANIMAL</a>
            <a href="${pageContext.request.contextPath}/animals">ALL ANIMALS</a>
        </div>
    </div>
</header>
<div class="account">
    <main class="page-main">
        <div class="columns p-col">
            <div class="column main">
                <h1 class="page-title">
                    <span class="base">Write details:</span>
                </h1>
                <div>
                    <form action="${pageContext.request.contextPath}/new-animal" method="post">
                        <div class="styled-input">
                            <span class="pr-2">NAME</span>
                            <input id="name" name="name" value="${name}" type="text" required
                                   maxlength="35" class="m-input" placeholder="ENTER ANIMAL NAME">
                        </div>
                        <div>
                            <span class="pr-2">TYPE</span>
                            <select id="type" class="m-input" name="type" required>
                                <option value="" disabled selected>SELECT TYPE...</option>
                                <c:forEach items="${types}" var="optionType">
                                    <option value="${optionType}" ${type.equals(String.valueOf(optionType)) ? 'selected' : ''}>
                                        <c:out value="${optionType}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="styled-input">
                            <span class="pr-2">BIRTH</span>
                            <input type="date" name="birth" value="${birth}"
                                   class="m-input" placeholder="ENTER DATE OF BIRTH" required aria-required="true">
                        </div>
                        <button id="start" type="submit" class="button">ADD ANIMAL</button>
                    </form>
                    <div id="error-block" class="pos-relative">
                        <c:if test="${violations != null}">
                            <div class="wrapper-msg wrap-text pos-absolute abs-top">
                                <c:forEach items="${violations}" var="violation">
                                    <p class="text-center text-red"><c:out value="${violation}"/></p>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="sidebar sidebar-main">
                <div class="block">
                    <div class="content">
                        <div class="left-info">
                            <div class="left-info__info left_flex">
                                <div class="left-info__name">
                                    <p>New Animal</p>
                                </div>
                            </div>
                            <div>
                                <img class="title-img img-fluid" src="images/farm.jpg"
                                     alt="farm">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<footer>
    <div class="wrap">
        <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-center d-block">Plotnikov Dmitriy © 2024</span>
            <span class="d-block text-center">New Animal</span>
        </div>
    </div>
</footer>
</body>
</html>