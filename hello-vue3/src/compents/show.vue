<template>
    <div class="image-scroll">
        <div v-for="item in props.s" :key="item.id">
            <img :src="item.img_url" class="scroll-img" />
            {{ item.id }}
    </div>
</div>



</template>



<style scoped>
    .image-scroll {
        display: flex;
        flex-direction: row;
        overflow-x: auto;
        white-space: nowrap;
        padding: 10px;
        scroll-behavior: smooth;
        gap: 10px;
        border: 1px solid #ccc;
    }

    .scroll-img {
    height: 150px;
    flex-shrink: 0;
    border-radius: 5px;
    }

</style>

<script setup>
    const props = defineProps({
        s: Array
    })

</script>