package com.clay.informhalal

import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat

enum class modelRumahMakan private constructor(
    val nama: String,
    val jenis: String,
    val kelurahan: String,
    val alamat: String,
    val lokasi: LatLng,
    var jadwalBuka: Int,
    var jadwalTutup: Int,
    val imageResIdResId: Int
){
    RM_001(
        "Rumah Makan Persada",
        "Masakan Padang",
        "Kota Raja",
        "Jl. Herewilla No.23, Naikoten II, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim.",
        LatLng(
            -10.1753701,123.5923084
        ),
        1000,
        2000,
        R.drawable.halal
    ),
    RM_002(
        "Rumah Makan Sederhana",
        "Masakan Padang",
        "Kelapa Lima",
        "Jl. Cak Doko No.9, Oebobo, Kota Kupang, Nusa Tenggara Tim.",
        LatLng(
            10.1696694,123.5825793
        ),
        1000,
        2000,
        R.drawable.halal
    )
    ,    RM_003(
        "PERSADA",
        "RUMAH MAKAN",
        "Naikoten II",
        "Jl. Herewilla No.23, Naikoten II, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim.",
        LatLng(
            -10.1692197,
            123.5852633
        ),
        1000,
        2000,
        R.drawable.halal
    )
    ,    RM_004(
        "PERSADA",
        "RUMAH MAKAN",
        "Naikoten II",
        "Jl. Herewilla No.23, Naikoten II, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim.",
        LatLng(
            -10.1692197,
            123.5852633
        ),
        1000,
        2000,
        R.drawable.halal
    )
    ,    RM_005(
        "PERSADA",
        "RUMAH MAKAN",
        "Naikoten II",
        "Jl. Herewilla No.23, Naikoten II, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim.",
        LatLng(
            -10.1692197,
            123.5852633
        ),
        1000,
        2000,
        R.drawable.halal
    )
    ,    RM_006(
        "PERSADA",
        "RUMAH MAKAN",
        "Naikoten II",
        "Jl. Herewilla No.23, Naikoten II, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim.",
        LatLng(
            -10.1692197,
            123.5852633
        ),
        1000,
        2000,
        R.drawable.halal
    )
    ,    RM_007(
        "PERSADA",
        "RUMAH MAKAN",
        "Naikoten II",
        "Jl. Herewilla No.23, Naikoten II, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim.",
        LatLng(
            -10.1692197,
            123.5852633
        ),
        1000,
        2000,
        R.drawable.halal
    );

    fun getJadwal(): String {
        val dateAwal = SimpleDateFormat("hhmm").parse(String.format("%04d", this.jadwalBuka))
        val dateAkhir = SimpleDateFormat("hhmm").parse(String.format("%04d", this.jadwalTutup))

        val sdf = SimpleDateFormat("HH:mm ")

        return sdf.format(dateAwal) + "-" + sdf.format(dateAkhir) + "WITA"

    }
}