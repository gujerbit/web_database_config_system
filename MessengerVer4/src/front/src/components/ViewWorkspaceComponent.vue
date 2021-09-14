<template>
    <div class="wrap">
        <div class="workspace">
            <p>WORKSPACE</p>
            <textarea @input="setQuery($event)" v-if="table !== ''" />
            <div class="query-btn" v-if="table !== ''">
                쿼리 실행
            </div>
        </div>
        <view-result-component />
    </div>
</template>

<script>
import viewResultComponent from './ViewResultComponent.vue';

export default {
    data() {
        return {
            query: ''
        }
    },
    mounted() {

    },
    methods: {
        setQuery(event) {
            this.query = event.currentTarget.value;
        }
    },
    components: {
        viewResultComponent
    },
    props: ['']
}
</script>

<style scoped>
.wrap {
    display: grid;
    grid-template-rows: 1fr 1fr;
}

.workspace {
    border: 2px solid #000000;
    margin: 1%;
}

.workspace textarea {
    width: 100%;
    height: 75%;
    outline: 0;
    background-color: #000000;
    color: #ffffff;
}

.query-btn {
    width: 20%;
    height: 10%;
    background-color: #ffffff;
    text-align: center;
    border: 1px solid #000000;
}

.query-btn:hover {
    background-color: #333333;
    color: #dddddd;
    transition: all 1s;
    cursor: pointer;
}

.wrap p {
    font-size: 1.5rem;
    text-align: center;
    height: 10%;
}
</style>