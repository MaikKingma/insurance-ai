<script lang="ts">
    import {ProductQueriesApi, type ProductView} from "$lib/api";
    import {Button} from "$lib/components/ui/button";
    import {apiConfig} from "$lib/utils";
    import ProductBasketItem from "./ProductBasketItem.svelte";
    import IconParkSolidShoppingBag from '~icons/icon-park-solid/shopping-bag';
    let productsInBasket = $state<ProductView[]>([]);
    const productApi = new ProductQueriesApi(apiConfig)
    productApi.getAllProducts().then(products => {
        productsInBasket = [products[0], products[1]]
        console.log(productsInBasket)
    })
</script>

<div class="p-4 border border-gray-800 rounded-xl w-full flex flex-col gap-4">
    <header class="flex items-center gap-2"><IconParkSolidShoppingBag /><h3 class="text-xl">Brilliant Basket</h3></header>
    {#each productsInBasket as product}
        <ProductBasketItem product={product}></ProductBasketItem>
    {/each}
    <Button>Buy!</Button>
</div>
