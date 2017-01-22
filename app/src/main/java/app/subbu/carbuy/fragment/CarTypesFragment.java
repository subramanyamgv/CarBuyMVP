package app.subbu.carbuy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import app.subbu.carbuy.R;
import app.subbu.carbuy.activity.CarTypesDialogActivity;
import app.subbu.carbuy.adapter.CarTypesListAdapter;
import app.subbu.carbuy.entity.CarType;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.mvp.model.CarTypes;
import app.subbu.mvp.presenter.CarTypesPresenter;
import app.subbu.mvp.view.CarTypesView;
import app.subbu.repository.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class CarTypesFragment extends Fragment implements CarTypesView, BaseQuickAdapter.RequestLoadMoreListener {

    public static final String TAG = "CarTypesFragment";

    @Inject
    CarTypesPresenter mCarTypesPresenter;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private CarTypesListAdapter mCarTypesListAdapter;
    private int currentPage = 0;

    public static CarTypesFragment newInstance() {
        return new CarTypesFragment();
    }

    public static void start(int contentViewId, FragmentManager fragmentManager) {
        CarTypesFragment fragment = newInstance();
        fragmentManager.beginTransaction().replace(contentViewId, fragment,
                TAG).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        mCarTypesPresenter.onCreate();
    }

    private void injectDependencies() {

        if (getActivity() instanceof CarTypesDialogActivity) {
            CarTypesDialogActivity activity = (CarTypesDialogActivity) getActivity();
            CarSelectionComponent carSelectionComponent = activity.getCarSelectionComponent();
            carSelectionComponent.inject(this);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_types, container, false);
        ButterKnife.bind(this, view);

        //Init Adapter
        mCarTypesListAdapter = new CarTypesListAdapter(new LinkedList<CarType>());

        //Init List
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCarTypesListAdapter);

        //Init Presenter
        mCarTypesPresenter.attachView(this);

        //Set Load more call back
        mCarTypesListAdapter.setOnLoadMoreListener(this);

        return view;
    }

    @Override
    public void onLoadMoreRequested() {
        mCarTypesPresenter.getCarTypes(currentPage + 1, Repository.DEFAULT_PAGE_SIZE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mCarTypesPresenter.onStart();
        mCarTypesPresenter.getCarTypes(0, Repository.DEFAULT_PAGE_SIZE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mCarTypesPresenter.onStop();
    }

    public void addCarTypes(CarTypes carTypes) {

        List<CarType> data = new LinkedList<>();

        int itemType = 0;

        for (Map.Entry<String, String> entry : carTypes.getWkda().entrySet()) {

            CarType carType = new CarType(itemType, entry.getKey(), entry.getValue());
            data.add(carType);

            itemType = itemType ^ 1;
        }

        currentPage = carTypes.getPage();

        mCarTypesListAdapter.addData(data);

        if (data.size() > 0)
            mCarTypesListAdapter.loadMoreComplete();
        else
            mCarTypesListAdapter.loadMoreEnd(true);
    }

    @Override
    public void showLoading() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mCarTypesListAdapter.setEmptyView(R.layout.loading_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showEmpty() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mCarTypesListAdapter.setEmptyView(R.layout.empty_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showCarTypes(final CarTypes carTypes) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                addCarTypes(carTypes);
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showError(Throwable e) {

    }
}
