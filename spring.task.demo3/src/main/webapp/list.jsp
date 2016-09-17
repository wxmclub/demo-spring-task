<%--
  Created by IntelliJ IDEA.
  User: Ming
  Date: 16/9/17
  Time: 下午10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="cn">
<head>
  <meta charset="UTF-8">
  <title>首页</title>
</head>
<body>

<table>
  <thead>
    <tr>
      <td>jobId</td>
      <td>jobGroup</td>
      <td>jobName</td>
      <td>jobStatus</td>
      <td>cronExpression</td>
      <td>desc</td>
      <td>操作</td>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${allJobs }" var="job">
    <tr>
      <td>${job.jobId}</td>
      <td>${job.jobGroup}</td>
      <td>${job.jobName}</td>
      <td>${job.jobStatus}</td>
      <td>${job.cronExpression}</td>
      <td>${job.desc}</td>
      <td>操作</td>
    </tr>
  </c:forEach>

  </tbody>
</table>



</body>
</html>
