package com.futura.app.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.futura.app.R
import com.futura.app.ui.mvi.actions.HomeAction
import com.futura.app.ui.mvi.bases.BaseView
import com.futura.app.ui.mvi.changes.HomeChange
import com.futura.app.ui.viewmodel.HomeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.stateViewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@FlowPreview
class HomeFragment: BaseView<HomeAction, HomeChange, HomeViewModel>(), OnMapReadyCallback {

    override val viewModel: HomeViewModel by stateViewModel()
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun setActors() {

    }

    override fun render(change: HomeChange) {
        when(change.type){
            HomeChange.State.Init -> {
                renderInit()
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val mex =LatLng(20.7366535,-103.420136)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mex))
        mMap.addPolyline(PolylineOptions()
            .clickable(false)
            .add(
                LatLng(20.7059579,-103.4035093),
                LatLng(20.7606896,-103.4002913)
            ))
    }

    private fun renderInit(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}