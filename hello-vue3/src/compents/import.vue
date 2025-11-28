<template>
    <br></br>
    <input v-model="name"  placeholder="商品名称" required/>
    <br></br>
    <input v-model="price" placeholder="该商品的价格" required/>
    <br></br>
    <input v-model="category" placeholder="该商品的种类" required/>
    <br></br>
    
    <!-- <br></br> -->
    <input v-model="stock" placeholder="商品库存" required/>
    <br></br>
    <input v-model="introduce "  @keyup.enter="add" placeholder="商品介绍" required/>
    <br></br>
    <input v-model="img_url" type = "url" placeholder="商品图片url" required/>
    <br></br>
    <input type="file" @change="handleFile" accept="image/*" />
    <button @click="uploadImage" :disabled="!file" >上传</button>
    <!-- <el-button @click="add" type="primary" round>Save</el-button> -->
     
    <button @click="add" :disabled="!name">Save</button>
    <br></br>
    <p v-if="!name" style="color: red">未输入商品名称</p>


    <p v-if="!img_url" style="color: red">暂未选择图片</p>
    <p v-else style="color: green">
        图片选择成功!
        <br></br>
        图片预览:
        <img :src="img_url" width="50"></img>
    </p>
    <br></br>


</template>


<script setup>
    import { ref } from 'vue';
    const name = ref(""),img_url = ref(""),category = ref("")
    const price = ref(""),stock = ref(""),introduce = ref("")

    function add(){
        axios.get("/api/add", {
            params: {
                name: name.value,
                img_url: img_url.value,
                category: category.value,
                price: price.value,
                stock: stock.value,
                introduce: introduce.value
            }
        }).then((data)=>{getList();})
    }

</script>