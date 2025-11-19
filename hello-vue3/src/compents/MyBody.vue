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

        {{ item.name  }}
        $: {{ item.price }}
        <br></br>
        <img :src=item.img_url width="200" />

    </p>


    <br></br>
    <input v-model="name"  placeholder="商品名称"></input>
    <br></br>
    <input v-model="price" placeholder="该商品的价格"></input>
    <br></br>
    <input v-model="img_url" type = "url" placeholder="商品图片url"></input>
    <br></br>
    <input v-model="stock" type = "url" placeholder="商品库存"></input>
    <br></br>
    <input v-model="introduce" type = "url" placeholder="商品介绍"></input>
    <br></br>



    
    <button @click="add">save</button>
    <br></br>



    <hr></hr>
    <br></br>
    <input v-model="delname"  @keyup.enter="del"></input>
    <br></br>
    <button @click="del">del</button>

    <p>
        <img src="/img/ying.jpg" />
    </p>


    <button @click="deleteAll"> deleteAll</button>


</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';



    const s = ref([]);
    const delname = ref("")
    const name = ref(""),img_url = ref(""),category = ref("")
    const price = ref(""),stock = ref(""),introduce = ref("")

    
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
    function del(){
        axios.get("http://localhost:8080/del",{
            params: {
                name: delname.value
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