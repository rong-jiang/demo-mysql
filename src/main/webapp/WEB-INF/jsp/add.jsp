<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <title>书本新增</title>
    <form   action="addBook" method="post">
        <table style="background-color: aqua" width="0px">
            <tr>
                <th>姓名:</th>
                <th><input type="text" name="name" /></th>
            </tr>
            <tr>
                <th>性別:</th>
                <th><input type="text" name="setb" /></th>
            </tr>
            <tr>
                <th>年龄:</th>
                <th><input type="text" name="age" /></th>
            </tr>
            <tr>
                <th>
                    <input name="on" type="submit" value="提交"/>
                </th>
            </tr>
        </table>
    </form>

</head>
<body>

</body>
</html>
