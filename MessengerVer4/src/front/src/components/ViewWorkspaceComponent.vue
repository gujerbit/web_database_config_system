<template>
    <div class="wrap">
        <div class="workspace">
            <p>WORKSPACE</p>
            <textarea @input="setQuery($event)" />
            <div @click="sendQuery()" class="query-btn">
                쿼리 실행
            </div>
        </div>
        <view-result-component :content="content" :result="result" />
    </div>
</template>

<script>
import viewResultComponent from './ViewResultComponent.vue';

export default {
    data() {
        return {
            query: '',
            result: '',
        }
    },
    mounted() {
    },
    methods: {
        setQuery(event) {
            this.query = event.currentTarget.value;
        },
        async sendQuery() {
            let header = {
                'jwt-auth-token': this.storage.getItem('jwt-auth-token')
            };
            if('select'.toUpperCase() === this.query.split(' ')[0].toUpperCase()) {
                let { data } = await this.axios.post('/query/select', {query: this.query}, {header});
                this.result = data;
            } else if('insert'.toUpperCase() === this.query.split(' ')[0].toUpperCase()
            || 'update'.toUpperCase() === this.query.split(' ')[0].toUpperCase()
            || 'delete'.toUpperCase() === this.query.split(' ')[0].toUpperCase()) {
                let { data } = await this.axios.post('/query/manipulation', {query: this.query}, {header});
                this.result = [{res: `Query OK, ${data} rows affected`}];
            } else {
                let { data } = await this.axios.post('/query/definition', {query: this.query}, {header});
                if(data) this.result = [{res: 'Query OK, 0 rows affected'}];
            }
        }
    },
    components: {
        viewResultComponent
    },
    props: ['content']
}
</script>

<style scoped>
.wrap {
    display: grid;
    grid-template-rows: repeat(2, 1fr);
    gap: 5px;
}

.workspace {
    width: 60%;
    border: 2px solid #000000;
    width: 800px;
    height: 300px;
}

p {
  font-size: 1.5rem;
  text-align: center;
}

textarea {
    width: 100%;
    height: 70%;
    background-color: #000000;
    color: #ffffff;
    font-size: 1.5rem;
}

.query-btn {
    border: 2px solid #000000;
    width: 15%;
    height: 15%;
    text-align: center;
    padding-top: 8px;
    margin-left: 84%;
}

.query-btn:hover {
    background-color: #000000;
    color: #ffffff;
    transition: all 1s;
}
</style>