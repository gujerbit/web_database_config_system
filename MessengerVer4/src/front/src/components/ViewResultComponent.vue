<template>
    <div class="result">
        <template v-if="result === ''">
            <div class="content" v-for="value in content" :key="value">
                <p class="element" v-for="item in Object.keys(value)" :key="item">
                        <pre>{{item + ' : ' + value[item]}}</pre>
                </p>
            </div>
        </template>
        <div class="content" v-for="value in result" :key="value">
            <p class="element" v-for="item in Object.keys(value)" :key="item">
                <pre>{{item + ' : ' + value[item]}}</pre>
            </p>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
        }
    },
    async mounted() {
        
    },
    methods: {

    },
    props: ['content', 'result']
}
</script>

<style scoped>
.result {
    border: 2px solid #000000;
    overflow: auto;
    width: 800px;
    height: 300px;
}

.content:nth-child(2n) {
    background-color: #000000;
    color: #ffffff;
}
</style>