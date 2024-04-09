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
                    <span class="base">Details:</span>
                </h1>
                <c:if test="${exception != null}">
                    <h1 class="invalid"><c:out value="${exception}"/></h1>
                </c:if>
                <c:if test="${exception == null}">
                    <div style="margin-bottom: 15px">
                        <div class="styled-input">
                            <span class="pr-2">NAME</span>
                            <input id="name" disabled name="name" value="${animal.getName()}" type="text"
                                   class="m-input">
                        </div>
                        <div class="styled-input">
                            <span class="pr-2">TYPE</span>
                            <input id="type" name="type" disabled value="${animal.getType()}" type="text"
                                   class="m-input">
                        </div>
                        <div class="styled-input">
                            <span class="pr-2">GROUP</span>
                            <input id="group" name="group" disabled
                                   value="${animal.getMainGroup() == 'PACK_ANIMAL' ? 'Pack animal' : 'Pet'}" type="text"
                                   class="m-input">
                        </div>
                        <div class="styled-input">
                            <span class="pr-2">BIRTH</span>
                            <input id="birth" name="birth" disabled value="${animal.getBirth()}" type="text"
                                   class="m-input">
                        </div>
                    </div>
                    <div id="main-block">
                        <h3 style="margin-top: 10px;: ">Save new command:</h3>
                        <div class="flex">
                            <div id="insert-select">
                                <h4>Choose from list:</h4>
                                <div class="flex">
                                    <div class="styled-input">
                                        <select class="m-input" id="existing-cmd">
                                            <option value="" disabled selected>CHOOSE...</option>
                                            <c:forEach items="${commands}" var="command">
                                                <option data-name="${command.getName()}"
                                                        data-text="${command.getText()}"
                                                        value="${command.getName()}"><c:out
                                                        value="${command.getName()}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div>
                                        <button disabled id="add" class="button btn-radius">ADD</button>
                                    </div>
                                </div>

                            </div>
                            <div id="create-new">
                                <h4>or create new:</h4>
                                <button id="new" class="button w70">NEW</button>
                            </div>
                        </div>
                    </div>
                    <div id="new-cmd" class="hide">
                        <div class="flex">
                            <div><h3 class="mt10">New command:</h3></div>
                            <div class="wrapper-btn-cmd">
                                <button id="save-cancel" class="button">CANCEL</button>
                            </div>
                        </div>
                        <div class="flex-sec">
                            <div class="styled-input">
                                <span class="pr-2">NAME</span>
                                <input maxlength="15" id="command-name" name="name" value="" type="text" required
                                       class="m-input">
                            </div>
                            <div class="styled-input">
                                <span class="pr-2">TEXT</span>
                                <input maxlength="23" id="command-text" name="type" value="" type="text" required
                                       class="m-input">
                            </div>
                            <div class="wrapper-btn-cmd">
                                <button id="save" class="button">SAVE</button>
                            </div>
                        </div>
                    </div>
                    <div id="error-block" class="pos-relative"></div>
                </c:if>
            </div>
            <div class="sidebar sidebar-main">
                <div class="block">
                    <div class="content">
                        <div class="left-info">
                            <c:if test="${exception == null}">
                                <div class="left-info__info left_flex pos-relative">
                                    <div class="left-info__name">
                                        <p>Commands:</p>
                                    </div>
                                    <div>
                                        <div class="flex">
                                            <div class="styled-input">
                                                <select class="m-input" id="list-cmd">
                                                    <option value="" disabled selected>CHOOSE...</option>
                                                    <c:forEach items="${animal.getAnimalsCommands()}" var="command">
                                                        <option data-name="${command.getName()}"
                                                                data-text="${command.getText()}"
                                                                value="${command.getName()}"><c:out
                                                                value="${command.getName()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div>
                                                <button id="btn-show" class="button btn-radius" disabled>
                                                    SHOW
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="styled-input mb12">
                                        <input id="show-command" disabled type="text" class="m-input">
                                    </div>

                                </div>
                            </c:if>
                            <div>
                                <c:if test="${exception != null}">
                                    <img class="title-img img-fluid" src="images/farm.jpg"
                                         alt="farm">
                                </c:if>
                                <c:if test="${exception == null}">
                                    <img class="title-img img-fluid" src="images/${animal.getType()}.jpg"
                                         alt="${animal.getType()}">
                                </c:if>
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
            <span class="d-block text-center">Profile</span>
        </div>
    </div>
</footer>
</body>
</html>