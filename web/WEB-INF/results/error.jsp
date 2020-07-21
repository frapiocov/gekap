<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore ${requestScope['javax.servlet.error.status_code']}"/>
</jsp:include>
<section style="margin-top: 50px">
    <h1>Errore ${requestScope['javax.servlet.error.status_code']}</h1>

    <pre>${requestScope['javax.servlet.error.exception']}</pre>
    <pre><%
        if (exception != null) {
            out.flush();
            exception.printStackTrace(response.getWriter());
        }
    %></pre>
</section>
<%@include file="footer.html"%>