<script lang="ts">
    import {ProductQueriesApi, type ProductView} from "$lib/api";
    import {apiConfig} from "$lib/utils";
    import ProductBasketItem from "./ProductBasketItem.svelte";
    import IconParkSolidShoppingBag from '~icons/icon-park-solid/shopping-bag';
    let productsInBasket = $state<ProductView[]>([]);
    const productApi = new ProductQueriesApi(apiConfig)
    productApi.getAllProducts().then(products => {
        productsInBasket = products
    })
</script>

<div class="p-4 border border-gray-800 rounded-xl w-full">
    <header class="flex items-center gap-2"><IconParkSolidShoppingBag /><h3 class="text-xl">Brilliant Basket</h3></header>
    {#each productsInBasket as product}
        <ProductBasketItem>{product}</ProductBasketItem>
    {/each}
</div>
