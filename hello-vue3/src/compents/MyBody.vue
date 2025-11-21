<template>
    <h1>商店交易系统</h1>
    <h3>制作人:
        张万里,周锐强,张云涛</h3>
    <p>学校:
        <a style="color:blueviolet;" href="https://www.cwxu.edu.cn/"title="无锡学院官网链接">
            Wuxi University
        </a>
    </p>
    <hr>

    <!-- <button @click="deleteAll">deleteAllData</button> -->

    <p v-for="item in s">

        {{ item.name  }}  商品id: {{ item.id }}
        <br></br>
        $: {{ item.price }} 
        <br></br>
        <img :src=item.img_url width="200" />

    </p>


    <br></br>
    <input v-model="name"  placeholder="商品名称"></input>
    <br></br>
    <input v-model="price" placeholder="该商品的价格"></input>
    <br></br>
    <input v-model="category" placeholder="该商品的种类"></input>
    <br></br>
    <input v-model="img_url" type = "url" placeholder="商品图片url"></input>
    <br></br>
    <input v-model="stock" placeholder="商品库存"></input>
    <br></br>
    <input v-model="introduce "  @keyup.enter="add" placeholder="商品介绍"></input>
    <br></br>


    <button @click="add">Save</button>

    <hr></hr>

    <br></br>
    <input v-model="delId"  @keyup.enter="del"></input>
    <br></br>
    <button @click="del">DeleteById</button>

    <hr></hr>

    <button @click="deleteAll"> DeleteAll</button>

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

</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';



    const s = ref([]);
    const delId = ref("")
    const name = ref(""),img_url = ref(""),category = ref("")
    const price = ref(""),stock = ref(""),introduce = ref("")

    const update_id = ref("")
    const update_name = ref(""),update_img_url = ref(""),update_category = ref("")
    const update_price = ref(""),update_stock = ref(""),update_introduce = ref("")


    function add(){
        axios.get("http://localhost:8080/add", {
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

    function update(){
        axios.get("http://localhost:8080/update", {
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
        axios.get("http://localhost:8080/del",{
            params: {
                id: delId.value
            }
        }).then((data)=>getList())
    }

    function deleteAll(){
        axios.get("http://localhost:8080/delAll").then((data)=>{s.value = []})
    }
    
    function getList(){
        axios.get("http://localhost:8080/list").then((data)=>{
            // console.log(data.data[0].name)
            s.value = data.data
        })
    }
    
    onMounted(()=>{
        getList()
    })


</script>