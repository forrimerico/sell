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
                <div class="col-md-12 column">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                手机号
                            </th>
                            <th>
                                地址
                            </th>
                            <th>
                                金额
                            </th>
                            <th>
                                订单状态
                            </th>
                            <th>
                                支付方式
                            </th>
                            <th>
                                支付状态
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                            <td>
                            ${orderDTO.orderId}
                            </td>
                            <td>
                            ${orderDTO.buyerName}
                            </td>
                            <td>
                            ${orderDTO.buyerPhone}
                            </td>
                            <td>
                            ${orderDTO.buyerAddress}
                            </td>
                            <td>
                            ${orderDTO.orderAmount}
                            </td>
                            <td>
                            ${orderDTO.getOrderStatusEnum().msg}
                            </td>
                        <td>
                            微信
                        </td>
                            <td>
                            ${orderDTO.getPayStatusEnum().msg}
                            </td>
                            <td>
                            ${orderDTO.createTime}
                            </td>
                            <td>
                        <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                            <#if orderDTO.getOrderStatusEnum().code != 2>
                                <a href="/sell/seller/order/cancle?orderId=${orderDTO.orderId}">取消</a>
                            </#if>
                            </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1><li><a class="disabled" href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}">上一页</a></li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="/sell/seller/order/list?page=${index}">${index}</a></li>
                            <#else>
                                <li>
                                <a href="/sell/seller/order/list?page=${index}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li><a class="disabled" href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>