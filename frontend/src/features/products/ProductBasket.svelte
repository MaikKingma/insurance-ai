<script lang="ts">
    import ProductBasketItem from "./ProductBasketItem.svelte";
    import IconParkSolidShoppingBag from '~icons/icon-park-solid/shopping-bag';
    let productsInBasket = $state(["product 1"]);
</script>

<div class="p-4 border border-gray-800 rounded-xl w-full">
    <header class="flex items-center gap-2"><IconParkSolidShoppingBag /><h3 class="text-xl">Brilliant Basket</h3></header>
    {#each productsInBasket as product}
        <ProductBasketItem>{product}</ProductBasketItem>
    {/each}
</div>
