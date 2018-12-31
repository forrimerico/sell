<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>卖家</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
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
                        ${orderDTO.getOrderStatus().msg}
                    </td>
                    <td>
                        微信
                    </td>
                    <td>
                        ${orderDTO.getPayStatus().msg}
                    </td>
                    <td>
                        ${orderDTO.createTime}
                    </td>
                    <td>
                        <a>详情</a>
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
</body>
</html>