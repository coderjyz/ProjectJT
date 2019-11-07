<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<table class="easyui-datagrid" id="itemList" title="商品列表"
       data-options=
               "singleSelect:false, <%--是否是单选表--%>
               collapsible:true,    <%--是否能折叠，右上角会有一个折叠按钮--%>
               pagination:true,     <%--是否有页码插件--%>
               url:'/item/findPageObject', <%--查询单页记录的URL--%>
               method:'get',        <%--请求方式--%>
               pageSize:20,         <%--页面大小--%>
               toolbar:toolbar"     <%--工具栏，需要在js中自己定义--%>
>
    <thead>
    <tr>
        <%--EasyUI中的field属性需要与实体类的属性名称一致--%>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:60">商品ID</th>
        <th data-options="field:'title',width:200">商品标题</th>
        <th data-options="field:'cid',width:100,align:'center',formatter:KindEditorUtil.findItemName">叶子类目</th>
        <th data-options="field:'sellPoint',width:100">卖点</th>
        <th data-options="field:'price',width:70,align:'right',formatter:KindEditorUtil.formatPrice">价格</th>
        <th data-options="field:'num',width:70,align:'right'">库存数量</th>
        <th data-options="field:'barcode',width:100">条形码</th>
        <th data-options="field:'status',width:60,align:'center',formatter:KindEditorUtil.formatItemStatus">状态</th>
        <th data-options="field:'created',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">创建日期</th>
        <th data-options="field:'updated',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">更新日期</th>
    </tr>
    </thead>
</table>
<div id="itemEditWindow"
     class="easyui-window"
     title="编辑商品"
     data-options="modal:true,
     closed:true,
     iconCls:'icon-save',
     href:'/page/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>
    function getSelectionsIds() {
        //获得复选框中选中的数据
        var items = $("#itemList").datagrid("getSelections");
        var ids = [];
        for (var i in items) {
            ids.push(items[i].id);
        }
        //将数据合并并用逗号隔开，很关键的操作，否则在进行异步请求的时候数据格式在后台会不识别
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [{
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            /* 因为新增商品是一个单独页面，所以让选择器指向左侧新增商品的按钮上点击跳转即可*/
            $(".tree-title:contains('新增商品')").parent().click();
        }
    }, {
        text: '编辑',
        iconCls: 'icon-edit',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("提示", "请选择一个商品才能编辑。");
                return;
            }
            if (ids.indexOf(',') > 0) {
                $.messager.alert("提示", "只能选择一个商品编辑。");
                return;
            }

            $("#itemEditWindow").window({
                onLoad :function(){
                    //回显数据
                    var data = $("#itemList").datagrid("getSelections")[0];
                    data.priceView = KindEditorUtil.formatPrice(data.price);
                    $("#itemEditForm").form("load",data);

                    // 加载商品描述, 回显商品的描述信息
                    $.getJSON('/query/item/desc/'+data.id,function(_data){
                        if(_data.status == 200){
                            //UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);
                            itemEditEditor.html(_data.data.itemDesc);
                        }
                    });

                    //加载商品规格
                    $.getJSON('/param/item/query/'+data.id,function(_data){
                        if(_data && _data.status == 200 && _data.data && _data.data.paramData){
                            $("#itemEditForm .params").show();
                            $("#itemEditForm [name=itemParams]").val(_data.data.paramData);
                            $("#itemEditForm [name=itemParamId]").val(_data.data.id);

                            //回显商品规格
                            var paramData = JSON.parse(_data.data.paramData);

                            var html = "<ul>";
                            for(var i in paramData){
                                var pd = paramData[i];
                                html+="<li><table>";
                                html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";

                                for(var j in pd.params){
                                    var ps = pd.params[j];
                                    html+="<tr><td class=\"param\"><span>"+ps.k+"</span>: </td><td><input autocomplete=\"off\" type=\"text\" value='"+ps.v+"'/></td></tr>";
                                }

                                html+="</li></table>";
                            }
                            html+= "</ul>";
                            $("#itemEditForm .params td").eq(1).html(html);
                        }
                    });

                    KindEditorUtil.init({
                        "pics" : data.image,
                        "cid" : data.cid,
                        fun:function(node){
                            KindEditorUtil.changeItemParam(node, "itemEditForm");
                        }
                    });
                }
            }).window("open");
        }
    }, {
        text: "删除",
        iconCls: 'icon-cancel',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("提示", "请至少选择一个商品进行删除。");
                return;
            }
            $.messager.confirm("确认", "确定删除id为" + ids + "的商品吗？", function (result) {
                if (result) {
                    var params = {"ids": ids};
                    console.log(params)
                    $.post("/item/deleteItems", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert("提示", "商品删除成功！", 'info', function () {
                                $("#itemList").datagrid("reload");
                            })
                        }else{
                            $.messager.alert("提示",data.msg);
                        }
                    })
                }
            })
        }
    },{
        text: "上架",
        iconCls: 'icon-remove',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("提示", "请至少选择一个商品进行上架。");
                return;
            }
            $.messager.confirm("确认", "确定上架id为" + ids + "的商品吗？", function (result) {
                if (result) {
                    var params = {"ids": ids};
                    $.post("/item/reshelf", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert("提示", "商品上架成功！", 'info', function () {
                                $("#itemList").datagrid("reload");
                            })
                        }
                    })
                }
            })
        }
    },{
        text: "下架",
        iconCls: 'icon-remove',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("提示", "请至少选择一个商品进行下架。");
                return;
            }
            $.messager.confirm("确认", "确定下架id为" + ids + "的商品吗？", function (result) {
                if (result) {
                    var params = {"ids": ids};
                    $.post("/item/instock", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert("提示", "商品下架成功！", 'info', function () {
                                $("#itemList").datagrid("reload");
                            })
                        }
                    })
                }
            })
        }
    }
    ]

</script>