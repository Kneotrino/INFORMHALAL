package com.clay.informhalal

import java.text.SimpleDateFormat

enum class modelRumahMakan private constructor(
    val alamat: String,
    val loc_lat: Double,
    val loc_lng: Double,
    val place_id: String,
    val nama: String,
    val kode: String,
    val rating: Double,
    var jadwalBuka: Int,
    var jadwalTutup: Int,
    val imageResIdResId: Int
){
    RM_001(
        "Jl. Cak Doko No.9, Oebobo, Kota Kupang, Nusa Tenggara Tim., Indonesia",
        -10.1669901,
        123.5972566,
        "a99a9db29fa2ab6b6a950be12616680c63b004d5",
        "Rumah Makan Sederhana - Masakan Padang",
        "RHMW+6W Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        3.9,
        1000,
        2000,
        R.drawable.halal
    ),
    RM_002(
        "Jl. Frans Seda, Oesapa Bar., Klp. Lima, Kota Kupang, Nusa Tenggara Tim. 85000, Indonesia",
        -10.155912,
        123.630829,
        "5152f23af12ab667258b01db6d529a955abf1a52",
        "Rumah Makan Padang 2",
        "RJVJ+J8 Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        4.1,
        1000,
        2000,
         R.drawable.halal
    ),
    RM_003(
        "JL W.J Lalamentik, RT 08 RW 02, Oebufu Oebufu Oebobo, Nusa Tenggara Tim. 85000, Oebufu, Oebobo, Kota Kupang, Nusa Tenggara Tim.",
        -10.1748991,
        123.6262372,
        "8e26006e57ec627ac2cc54e4b101115f8b1ff50a",
        "Rumah Makan Titian Pauh",
        "RJGG+2F Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        4.3,
        1000,
        2000,
        R.drawable.halal
    ),
    RM_004(
        "Jl. Herewilla No.23, Naikoten II, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim., Indonesia",
        -10.1753701,
        123.5966858,
        "f718201e27107c2f16d9c0c2db9769dc5fedba6b",
        "Rumah Makan Persada",
        "RHFW+VM Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        4.3,
        1000,
        2000,
        R.drawable.halal

    ),
    RM_005(
        "Jl. Yos Sudarso, Alak, Kota Kupang, Nusa Tenggara Tim., Indonesia",
        -10.1943935,
        123.5325186,
        "fe4c52c57b6e011713f5a43ea3a05887afafe361",
        "Rumah Makan Simpang Raya",
        "RG4M+62 Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        3.2,
        1000,
        2000,
        R.drawable.halal
    ),
    RM_006(
        "Jl. Timor Raya No.62, Bakunase, Kec. Kota Raja, Kota Kupang, Nusa Tenggara Tim., Indonesia",
        -10.1833333,
        123.5833333,
        "a624864eac542f5244b3cad6b7b7537311a7fc59",
        "Simpang Raya Rumah Makan",
        "RH8M+M8 Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        .0,
        1000,
        2000,
        R.drawable.halal

    ),
    RM_007(
        "Jl. Urip Sumohardjo, Merdeka, Kec. Kota Lama, Kota Kupang, Nusa Tenggara Tim., Indonesia",
        -10.1613278,
        123.5841786,
        "b245e76ff6ba1a532bc332d5e5384fc39bf1e8e9",
        "RM. TELUK BAYUR",
        "RHQM+FM Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        3.9,
        1000,
        2000,
        R.drawable.halal

    ),
    RM_008(
        "Jl. Timor Raya No.1B, Klp. Lima, Kota Kupang, Nusa Tenggara Tim. 85228, Indonesia",
        -10.145299,
        123.615191,
        "3ebd77be48e160aa08153e154dd0f04019cdf9e4",
        "RM Bundo Kanduang",
        "VJ38+V3 Kupang, Kupang City, East Nusa Tenggara, Indonesia",
        4.1,
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