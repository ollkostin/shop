<select id="size-select"
        onchange="onSizeChange()">
    <option selected>5</option>
    <option>10</option>
</select>
<table class="table">
    <thead>
    <tr>
        <th>id</th>
        <th></th>
        <td>Product name</td>
        <td>Price</td>
        <td>Count</td>
    </tr>
    </thead>
    <tbody id="products"></tbody>
</table>
<#include 'pagination/pagination-buttons.ftl'/>