package controllers

import play.api.test._
import org.specs2.mutable._
import collection.immutable
import models._
import anorm.{Id, Pk}
import org.specs2.mock.Mockito
import helpers.Helper

class ShoppingCartSpec extends Specification with Mockito {
  "ShoppingCart" should {
    "getItemInfo can extract items in shopping cart" in {
      val cart: immutable.Seq[ShoppingCartTotalEntry] = List(
        ShoppingCartTotalEntry(
          ShoppingCartItem(
            id = mock[Pk[Long]],
            storeUserId = 0L,
            sequenceNumber = 0,
            siteId = 1,
            itemId = 2,
            quantity = 3
          ),
          itemName = mock[ItemName],
          itemDescription = mock[ItemDescription],
          site = Site(Id(1L), 0L, "site1"),
          itemPriceHistory = mock[ItemPriceHistory],
          taxHistory = mock[TaxHistory]
        ),

        ShoppingCartTotalEntry(
          ShoppingCartItem(
            id = mock[Pk[Long]],
            storeUserId = 0L,
            sequenceNumber = 1,
            siteId = 1,
            itemId = 3,
            quantity = 2
          ),
          itemName = mock[ItemName],
          itemDescription = mock[ItemDescription],
          site = Site(Id(1L), 0L, "site1"),
          itemPriceHistory = mock[ItemPriceHistory],
          taxHistory = mock[TaxHistory]
        )
      )

      Helper.doWith(
        controllers.ShoppingCart.getItemInfo(
          Map(), 
          cart
        )
      ) { newCart =>
        newCart.size === 0
      }

      Helper.doWith(
        controllers.ShoppingCart.getItemInfo(
          Map((1L, 2L) -> 2), 
          cart
        )
      ) { newCart =>
        newCart.size === 1
        Helper.doWith(newCart.head.shoppingCartItem) { item =>
          item.siteId === 1L
          item.itemId === 2L
          item.quantity === 2
        }
        newCart.head.itemName === cart(0).itemName
      }

      Helper.doWith(
        controllers.ShoppingCart.getItemInfo(
          Map((1L, 3L) -> 1), 
          cart
        )
      ) { newCart =>
        newCart.size === 1
        Helper.doWith(newCart.head.shoppingCartItem) { item =>
          item.siteId === 1L
          item.itemId === 3L
          item.quantity === 1
        }
        newCart.head.itemName === cart(1).itemName
      }
    }
  }
}
