<template>

    <h1>商店交易系统</h1>
    <h3>制作人:
        xxxx</h3>
    <p>学校:
        <a style="color:blueviolet;" href="https://www.cwxu.edu.cn/"title="无锡学院官网链接">
            Wuxi University
        </a>
    </p>
    <hr>

    
    <show :s="s" />

    <add :getList="getList" />

    <hr></hr>
    <br></br>
    <input v-model="delId"  @keyup.enter="del"></input>
    <br></br>
    <button @click="del">DeleteById</button>
    <br></br>
    <hr></hr>




    <br></br>
    <input v-model="update_id"  placeholder="所需更新商品的id编号"></input>
    <br></br>
    <input v-model="update_name"  placeholder="更新商品名称"></input>
    <br></br>
    <input v-model="update_price" placeholder="该更新商品的价格"></input>
    <br></br>
    <input v-model="update_category" placeholder="该更新商品的种类"></input>
    <br></br>
    <input v-model="update_img_url" type = "url" placeholder="更新商品图片url"></input>
    <br></br>
    <input v-model="update_stock" type = "url" placeholder="更新商品库存"></input>
    <br></br>
    <input v-model="update_introduce" type = "url" placeholder="更新商品介绍"></input>
    <br></br>
    <button @click="update"> update</button>

    
    <p>
        <img src="/img/ying.jpg" />
    </p>
    <hr></hr>
    <button @click="deleteAll"> DeleteAll</button>

    
</template>




<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import Show from '../compents/show.vue';
import Add from '../compents/add.vue';

    const s = ref([]);
    const delId = ref("")
    const name = ref(""),img_url = ref(""),category = ref("")
    const price = ref(""),stock = ref(""),introduce = ref("")

    const update_id = ref("")
    const update_name = ref(""),update_img_url = ref(""),update_category = ref("")
    const update_price = ref(""),update_stock = ref(""),update_introduce = ref("")



    function update(){
        axios.get("/api/update", {
            params: {
                id: update_id.value,
                name: update_name.value,
                price: update_price.value,
                stock: update_stock.value,
                category: update_category.value,
                img_url: update_img_url.value,
                introduce: update_introduce.value
            }
        }).then((data)=>{getList();})
    }

    function del(){
        axios.get("/api/del",{
            params: {
                id: delId.value
            }
        }).then((data)=>getList())
    }

    function deleteAll(){
        axios.get("/api/delAll").then((data)=>{s.value = []})
    }
    
    function getList(){
        axios.get("/api/list").then((data)=>{
            // console.log(data.data[0].name)
            s.value = data.data
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
    
    onMounted(()=>{
        getList()   
    })


</script>