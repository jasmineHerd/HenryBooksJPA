<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Henry Books</title>
    </head>
    <style>
        .right {
            text-align: right;
        }
        .left {
            text-align: left;
        }
    </style>
    <c:if test="${!user.authenticated}">
        <script type="text/javascript">
            window.location = "/HenryBooksJPA";
        </script>    
    </c:if>
    <body>
        <h3>User: ${user.userID} - ${user.userName}, ${user.adminLevel} Level</h3>
        <br>Branch #: <b>${store.storeID}</b>
        <br>Branch Name: <b>${store.storeName}</b>
        <br>Branch Location: <b>${store.storeAddr}</b><br>
        <br>
        <c:if test="${user.adminLevel == 'Admn'}">
            <form action="SelectedBook" method="post" > 
                Book Cd: <input type="text" name="bookcd" id="bookcd"/>
                <input type="submit" value="Edit Record"><br>
            </form>
            <br>

            <form action ="IncAllBooks" method = "post">
                <input type ="submit" value="Increment All">              
            </form>
            <br>
            ${msg}
            <br>
        </c:if>



        <a href ="StoreSelection.jsp">Return to Store Selection</a>
        <table border ="1">
            <tr>
                <td>
                    <table border="1">
                        <tr>
                            <th>Store</th>
                            <th>Book Cd</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Pub Cd</th>
                            <th>Pub Nm</th>
                            <th>Retail Price</th>
                            <th>Quantity</th>

                        </tr>

                        <c:forEach var="inv" items="${store.bookinv}">

                            <tr>
                                <td class="right">${inv.storeID}</td>
                                <td class="right">${inv.bookID}</td>
                                <td class="left">${inv.book.title}</td>
                                <td class="left">${inv.book.author}</td>

                                <td class="left">${inv.book.pubcd}</td>
                                <c:forEach var="pub" items="${publishers}">
                                    <c:if test="${inv.book.pubcd == pub.pubcd}">
                                        <td class="left">${pub.pubname}</td></c:if>
                                </c:forEach>
                                
                               <td class="right"><fmt:formatNumber value = "${inv.book.price}" type = "currency"/></td>
                                <td class="right">${inv.onHand}</td>
                            </c:forEach>




                        </tr>

                    </table>

                </td>


            </tr>
        </table>
    </body>
</html>
