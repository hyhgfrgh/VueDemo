<template>
    <div class="image-scroll">
        <div v-for="item in s" :key="item.id">
            <img :src="item.img_url" class="scroll-img" />
            {{ item.id }}
    </div>
</div>



</template>



<style scoped>
    .image-scroll {
    display: flex;          /* 横向排列 */
    flex-direction: row;
    overflow-x: auto;       /* 超出部分允许横向滚动 */
    white-space: nowrap;    /* 防止换行 */
    padding: 10px;
    scroll-behavior: smooth;
    gap: 10px;              /* 图片间距 */
    border: 1px solid #ccc; /* 可选：显示滚动区域边框 */
    }

    .scroll-img {
    height: 150px;          /* 高度固定，宽度自适应 */
    flex-shrink: 0;         /* 防止被压缩 */
    border-radius: 5px;
    }

</style>

<script setup>


</script>