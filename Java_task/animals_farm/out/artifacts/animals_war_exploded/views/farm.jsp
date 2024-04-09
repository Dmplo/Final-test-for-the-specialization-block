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
                    <span class="base">Result of the last added animal:</span>
                </h1>
                <div id="error-block" class="pos-relative mt20">
                    <c:if test="${exception != null}">
                        <div class="wrapper-msg wrap-text pos-absolute abs-top">
                            <p class="text-center text-red"><c:out value="${exception}"/></p>
                        </div>
                    </c:if>
                </div>
                <c:if test="${exception == null}">
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th scope="col">№</th>
                                <th scope="col">NAME</th>
                                <th scope="col">TYPE</th>
                                <th scope="col">GROUP</th>
                                <th scope="col">BIRTH</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td data-label="№"><c:out value="${animal.getId()}"/></td>
                                <td data-label="NAME"><c:out value="${animal.getName()}"/></td>
                                <td data-label="TYPE"><c:out value="${animal.getType()}"/></td>
                                <td data-label="GROUP"><c:out
                                        value="${animal.getMainGroup() == 'PACK_ANIMAL' ? 'Pack animal' : 'Pet'}"/></td>
                                <td data-label="BIRTH"><c:out value="${animal.getBirth()}"/></td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="pos-relative mt20">
                            <c:if test="${create}">
                                <div class="wrapper-msg pos-absolute wrap-text">
                                    <p id="text-msg" class="text-green text-center">
                                        Animal "<c:out value="${animal.getType()}"/>"  named "<c:out
                                            value="${animal.getName()}"/>" has been successfully added!
                                    </p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="sidebar sidebar-main">
                <div class="block">
                    <div class="content">
                        <div class="left-info">
                            <div class="left-info__info left_flex">
                                <div class="left-info__name">
                                    <p>Animals farm</p>
                                </div>
                                <div class="left-info__b">
                                    <div class="left-info__b__item">
                                        <a href="${pageContext.request.contextPath}/new-animal">
                                            <button class="button">NEW ANIMAL</button>
                                        </a>
                                    </div>
                                    <div class="left-info__b__item">
                                        <a href="${pageContext.request.contextPath}/animals">
                                            <button class="button">ALL ANIMALS</button>
                                        </a>
                                    </div>
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
            <span class="d-block text-center">Animals farm</span>
        </div>
    </div>
</footer>
</body>
</html>