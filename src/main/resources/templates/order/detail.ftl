<!DOCTYPE html>
<html lang="en">
<#include "../Common/title.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--侧边栏-->
    <#include "../Common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <#--订单详情-->
                <div class="col-md-4 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                订单ID
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                ${orderDTO.orderId}
                            </td>
                            <td>
                                ${orderDTO.orderAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <#--具体商品列表详情-->
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                商品ID
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as detail>
                            <tr>
                            <td>${detail.detailId}</td>
                            <td>${detail.productName}</td>
                            <td>${detail.productPrice}</td>
                            <td>${detail.productQuantity}</td>
                            <td>${detail.productPrice * detail.productQuantity}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#if orderDTO.getOrderStatusEnum().code == 0>
                    <div class="col-md-12 column">
                    <a type="button" class="btn btn-default btn-success"
                    href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a>
                <a type="button" class="btn btn-default btn-danger"
                href="/sell/seller/order/cancle?orderId=${orderDTO.orderId}">取消订单</a>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>
</html>