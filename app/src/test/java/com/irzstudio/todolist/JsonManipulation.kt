package com.irzstudio.todolist

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Test

class JsonManipulation {
    @Test
    fun refactoryTest() {
        val data = Gson().fromJson<List<Inventory>>(json, object : TypeToken<List<Inventory>>(){}.type)
        println(itemOnMeetingRoom(data))

    }

    //1. Find items in the Meeting Room.
    fun itemOnMeetingRoom(data: List<Inventory>) : List<Inventory>{
        return data.filter { it.placement.room_id == 3 }
    }

    //2. Find all electronic devices.
    fun findElectronic(data: List<Inventory>) : List<Inventory>{
        return data.filter { it.type == "electronic" }
    }

    //3. Find all the furniture.
    fun findFurniture(data: List<Inventory>) : List<Inventory>{
        return data.filter { it.type == "furniture" }
    }

    //3. Find all items were purchased on 16 Januari 2020.
    //Unix time dari 16 Januari 2020 = 1579107600
    //Ref : https://www.unixtimestamp.com/
    fun findPurchaseAt(data: List<Inventory>) : List<Inventory>{
        return data.filter { it.purchased_at >  1579107600 }
    }

    //4. Find all items with brown color.
    fun findBrown(data: List<Inventory>) : List<Inventory>{
        return data.filter { it.tags.contains("brown") }
    }


    val json = "[\n" +
            "  {\n" +
            "    \"inventory_id\": 9382,\n" +
            "    \"name\": \"Brown Chair\",\n" +
            "    \"type\": \"furniture\",\n" +
            "    \"tags\": [\n" +
            "      \"chair\",\n" +
            "      \"furniture\",\n" +
            "      \"brown\"\n" +
            "    ],\n" +
            "    \"purchased_at\": 1579190471,\n" +
            "    \"placement\": {\n" +
            "      \"room_id\": 3,\n" +
            "      \"name\": \"Meeting Room\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"inventory_id\": 9380,\n" +
            "    \"name\": \"Big Desk\",\n" +
            "    \"type\": \"furniture\",\n" +
            "    \"tags\": [\n" +
            "      \"desk\",\n" +
            "      \"furniture\",\n" +
            "      \"brown\"\n" +
            "    ],\n" +
            "    \"purchased_at\": 1579190642,\n" +
            "    \"placement\": {\n" +
            "      \"room_id\": 3,\n" +
            "      \"name\": \"Meeting Room\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"inventory_id\": 2932,\n" +
            "    \"name\": \"LG Monitor 50 inch\",\n" +
            "    \"type\": \"electronic\",\n" +
            "    \"tags\": [\n" +
            "      \"monitor\"\n" +
            "    ],\n" +
            "    \"purchased_at\": 1579017842,\n" +
            "    \"placement\": {\n" +
            "      \"room_id\": 3,\n" +
            "      \"name\": \"Lavender\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"inventory_id\": 232,\n" +
            "    \"name\": \"Sharp Pendingin Ruangan 2PK\",\n" +
            "    \"type\": \"electronic\",\n" +
            "    \"tags\": [\n" +
            "      \"ac\"\n" +
            "    ],\n" +
            "    \"purchased_at\": 1578931442,\n" +
            "    \"placement\": {\n" +
            "      \"room_id\": 5,\n" +
            "      \"name\": \"Dhanapala\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"inventory_id\": 9382,\n" +
            "    \"name\": \"Alat Makan\",\n" +
            "    \"type\": \"tableware\",\n" +
            "    \"tags\": [\n" +
            "      \"spoon\",\n" +
            "      \"fork\",\n" +
            "      \"tableware\"\n" +
            "    ],\n" +
            "    \"purchased_at\": 1578672242,\n" +
            "    \"placement\": {\n" +
            "      \"room_id\": 10,\n" +
            "      \"name\": \"Rajawali\"\n" +
            "    }\n" +
            "  }\n" +
            "]\n"
}


data class Inventory(
    val inventory_id: String,
    val name: String,
    val type: String,
    val tags: List<String>,
    val purchased_at: Long,
    val placement: Placement
)

data class Placement(
    val room_id: Int,
    val name: String
)