<template>
    <p>hello</p>
    <div>
        
      
        <div v-if="s.stock == -1">
            没有商品
        </div>
        <div v-else>
            {{ s.name}}
            {{ s.stock}}
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