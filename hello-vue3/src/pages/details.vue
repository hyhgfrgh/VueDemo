<template>
    <div>
        
        <div v-if="s.stock == -1">
            所查询id编号暂无商品
        </div>
        <div v-else>
            <h1>商品详情:</h1>
            <h2>商品编号: {{ s.id}}</h2>
            <h2>商品名称: {{ s.name}}</h2>
            <h2>商品库存: {{ s.stock}}</h2>
            <h2>商品价格: {{ s.price}}</h2>
            <h2>商品介绍: {{ s.introduce}}</h2>
            <hr/>
            <h3>商品展示:</h3>
            <br/>
            <img :src="s.img_url"/>
        </div>
    </div>


</template>



<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';



    const s = ref({})
    const route = useRoute()

    function getDetails(){
        axios.get("api/find",{
            params:{
                id: route.query.id
            }
        }).then((data)=>{
            s.value = data.data;
        })
    }
    onMounted(()=>{
        getDetails()
    })


</script>