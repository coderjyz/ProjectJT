<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="itemEditForm" class="itemForm" method="post">
        <input type="hidden" name="id"/>
        <table cellpadding="5">
            <tr>
                <td>商品类目:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
                    <input type="hidden" name="cid" style="width: 280px;"></input>
                </td>
            </tr>
            <tr>
                <td>商品标题:</td>
                <td><input class="easyui-textbox" type="text" name="title" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>商品卖点:</td>
                <td><input class="easyui-textbox" name="sellPoint"
                           data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 280px;"></input></td>
            </tr>
            <tr>
                <td>商品价格:</td>
                <td><input class="easyui-numberbox" type="text" name="priceView"
                           data-options="min:1,max:99999999,precision:2,required:true"/>
                    <input type="hidden" name="price"/>
                </td>
            </tr>
            <tr>
                <td>库存数量:</td>
                <td><input class="easyui-numberbox" type="text" name="num"
                           data-options="min:1,max:99999999,precision:0,required:true"/></td>
            </tr>
            <tr>
                <td>条形码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'"/>
                </td>
            </tr>
            <tr>
                <td>商品图片:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
                    <input type="hidden" name="image"/>
                </td>
            </tr>
            <tr>
                <td>商品描述:</td>
                <td>
                    <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
                </td>
            </tr>
            <tr class="params hide">
                <td>商品规格:</td>
                <td>

                </td>
            </tr>
        </table>
        <input type="hidden" name="itemParams"/>
        <input type="hidden" name="itemParamId"/>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
    </div>
</div>
<script type="text/javascript">
    var itemEditEditor;
    $(function () {
        itemEditEditor = KindEditorUtil.createEditor("#itemEditForm [name=desc]")
        var data = $("#itemList").datagrid("getSelections")[0];
        var params = {"itemId": data.id}
        $.get('/item/query/item/desc', params, function (result) {
            console.log(result.status);
            if (result.status == 200) {
                //console.log("item/desc's data:" + result.data)
                //itemEditEditor指的是编辑页面里面的商品描述编辑框
                itemEditEditor.html(result.data);
            }
        })
    })

    function submitForm() {
        if (!$("#itemEditForm").form("validate")) {
            $.messager.alert('提示', '有必填选项没有填写!');
            return;
        }
        $("#itemEditForm [name=price]").val($("#itemEditForm [name=priceView]").val() * 100);
        itemEditEditor.sync();

        var paramJson = [];
        $("#itemeEditForm .params li").each(function(i,e){
            var trs = $(e).find("tr");
            var group = trs.eq(0).text();
            var ps = [];
            for(var i = 1;i<trs.length;i++){
                var tr = trs.eq(i);
                ps.push({
                    "k" : $.trim(tr.find("td").eq(0).find("span").text()),
                    "v" : $.trim(tr.find("input").val())
                });
            }
            paramJson.push({
                "group" : group,
                "params": ps
            });
        });
        paramJson = JSON.stringify(paramJson);

        $("#itemeEditForm [name=itemParams]").val(paramJson);
        $.post("/item/updateItem", $("#itemEditForm").serialize(), function (data) {
            if (data.status == 200) {
                $.messager.alert("提示", "商品修改成功!", 'info',function () {
                    $("#itemEditWindow").window("close");
                    $("#itemList").datagrid("reload");
                });
            } else {
                $.messager.alert("提示", data.message);
            }
        })

    }
</script>
