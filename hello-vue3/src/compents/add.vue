<template>

    <button @click="showDialog = true">新增商品</button>
    <div class="dialog-mask" v-if="showDialog">
        <div class="dialog-box">
            <h3>新增商品</h3>

            <input v-model="name"  placeholder="商品名称" required/>
            <br></br>
            <input v-model="price" placeholder="该商品的价格" required/>
            <br></br>
            <input v-model="category" placeholder="该商品的种类" required/>
            <br></br>
            
            <input v-model="stock" placeholder="商品库存" required/>
            <br></br>
            <!-- <input v-model="introduce "  @keyup.enter="add" placeholder="商品介绍" required/> -->
             <textarea 
                v-model="introduce"
                placeholder="商品介绍"
                rows="4"
                class="big-input"
                required
            ></textarea>
            <br></br>
            <input v-model="img_url" type = "url" placeholder="商品图片url" required/>
            <br></br>
            <input type="file" @change="handleFile" accept="image/*" />
            <button @click="uploadImage" :disabled="!file" >上传</button>
            
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

            <button @click="add">保存</button>
            <button @click="showDialog = false">取消</button>

            
        </div>
    </div>


</template>


<script setup>
    import axios from 'axios';
    import { ref } from 'vue';

    const props = defineProps({
        getList: Function
    })

    const name = ref(""),img_url = ref(""),category = ref(""),price = ref(""),stock = ref(""),introduce = ref("")

    const showDialog = ref(false)

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
        }).then((data)=>{
            showDialog.value = false;
            clearForm();
            props.getList();
        })
    }

    const file = ref(null)
    const previewUrl = ref(null)
    const result = ref(null)
    function handleFile(e) {
        file.value = e.target.files[0]
        previewUrl.value = URL.createObjectURL(file.value)
    }

    async function uploadImage() {
        if (!file.value) return alert("请选择图片")

        const form = new FormData()
        form.append("file", file.value)
        form.append("strategy_id", 1)

        const res = await axios.post("http://image.wanli.zhiyuansofts.cn/api/v1/upload", form, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        })
        result.value = res.data.data
        img_url.value = res.data.data.links.url
        file.value = ""
        name.value = ""
    }
    


    function clearForm() {
        name.value = "";
        img_url.value = "";
        category.value = "";
        price.value = "";
        stock.value = "";
        introduce.value = "";
    }
</script>


<style>
    .dialog-mask {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0,0,0,0.5);
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .dialog-box {
        background: white;
        padding: 20px;
        border-radius: 15px;
        width: 500px;
        height: 600px;
    }
    .big-input {
        width: 61%;
        padding: 8px;
        /* font-size: 14px; */
        /* border-radius: 6px; */
    }
</style>